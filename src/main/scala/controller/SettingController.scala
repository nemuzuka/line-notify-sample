package controller

import domain.user.User

class SettingController extends ApplicationController {

  beforeAction() {
    val userInfo = session.getAs[domain.user.User]("UserInfo").getOrElse(createDefaultUser)
    set("userInfo", userInfo)
  }

  def index: String = render("/setting/index")

  /**
   * デフォルトのログインユーザ情報生成.
   * @return ログインユーザ情報
   */
  private[this] def createDefaultUser: User = {
    val uuid = java.util.UUID.randomUUID.toString
    User(
      id = uuid,
      name = s"$uuid さん",
      loginId = "direct_login",
      token = None
    )
  }
}
