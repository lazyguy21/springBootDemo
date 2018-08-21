package org.yyf.springBootDemo.configuration.component;

import org.apache.commons.codec.binary.Base64;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.Resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by @author yyf on 2018/8/10.
 */
public class SignatureUtil {
  private static String PUBLIC_KEY_FILE = "PublicKey.txt";
  private static String PRIVATE_KEY_FILE = "PrivateKey.txt";

  public static void main(String[] args) {
    generatePbPrKey();
  }

  public static void generatePbPrKey() {
    KeyPairGenerator kpg;// 生成实现RSA算法的KeyPairGenerator对象
    try {
      kpg = KeyPairGenerator.getInstance("HmacSHA256");
      kpg.initialize(512);// 初始化确定密钥的大小
      KeyPair kp = kpg.generateKeyPair();// 生成密钥对
      PublicKey pbkey = kp.getPublic();// 创建公钥
      PrivateKey prkey = kp.getPrivate();// 创建私钥
      String publicKeyBase64Str = Base64.encodeBase64String(pbkey.getEncoded());
      System.out.println(publicKeyBase64Str);
      String privateKeyBase64Str = Base64.encodeBase64String(prkey.getEncoded());
      System.out.println(privateKeyBase64Str);

      /** 用对象流将生成的密钥写入文件 */
      FileOutputStream oos1 = new FileOutputStream(PUBLIC_KEY_FILE);
      oos1.write(publicKeyBase64Str.getBytes("UTF-8")); // 可以指定编码

      FileOutputStream oos2 = new FileOutputStream(PRIVATE_KEY_FILE);
      oos2.write(privateKeyBase64Str.getBytes("UTF-8")); // 可以指定编码

      /** 清空缓存，关闭文件输出流 */
      if (oos1 != null) {
        oos1.close();
      }
      if (oos2 != null) {
        oos2.close();
      }
    } catch (Exception e) {
      System.out.println("todo");
      throw new RuntimeException("no such algorithm", e);
    }
  }

  public static PublicKey loadPublicKey() {
    try {
      String publicKeyString = loadClassPathFile(PUBLIC_KEY_FILE);
      byte[] key = Base64.decodeBase64(publicKeyString);
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PublicKey publicKey = keyFactory.generatePublic(keySpec);
      return publicKey;
    } catch (Exception e) {
      //todo
      throw new RuntimeException(e);
    }
  }

  public static PrivateKey loadPrivateKey() {
    try {
      String privateKeyString = loadClassPathFile(PRIVATE_KEY_FILE);
      byte[] key = Base64.decodeBase64(privateKeyString);
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PrivateKey result = keyFactory.generatePrivate(keySpec);
      return result;
    } catch (Exception e) {
      //todo
      throw new RuntimeException(e);
    }
  }

  public static String sign(byte[] source) {
    Signature signature = null;
    try {
      signature = Signature.getInstance("MD5withRSA");
      signature.initSign(loadPrivateKey());
      signature.update(source);
      String signString = Base64.encodeBase64String(signature.sign());
      System.out.println(signString);
      return signString;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String loadClassPathFile(String fileName) {
    URL resource = Resources.getResource(fileName);
    try {
      InputStream inputStream = resource.openStream();
      String s = new String(ByteStreams.toByteArray(inputStream), Charsets.UTF_8);
      return s;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public Boolean validateSignature(String source, String signatureStr) {
    try {
      Signature signature = Signature.getInstance("MD5withRSA");
      signature.initVerify(loadPublicKey());
      signature.update(source.getBytes());
      boolean result = signature.verify(Base64.decodeBase64(signatureStr));
      return result;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
