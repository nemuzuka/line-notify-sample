package util

import skinny.SkinnyConfig

/**
 * 設定値を保持するHelper.
 */
object ConfigHelper {

  /** アプリケーションversion. */
  val appVersion = "1.0.1"

  //LINEの認証で使用する設定値
  /** clientId. */
  val lineClientId: String = SkinnyConfig.stringConfigValue("line.clientId").getOrElse(throw new RuntimeException("line.clientId is null."))
  /** clientId. */
  val lineClientSecret: String = SkinnyConfig.stringConfigValue("line.clientSecret").getOrElse(throw new RuntimeException("line.clientSecret is null."))
}
