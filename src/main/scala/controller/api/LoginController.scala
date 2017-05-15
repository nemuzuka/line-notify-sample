package controller.api

import controller.ApiController
import domain.user.User

class LoginController extends ApiController {

  def execute: String = {
    val uuid = java.util.UUID.randomUUID.toString
    val user = User(
      id = uuid,
      name = s"$uuid さん",
      loginId = params.getAs[String]("loginId").getOrElse("ろぐいんID"),
      token = None
    )
    session.invalidate()
    session += "UserInfo" -> user
    createJsonResult("")
  }
}
