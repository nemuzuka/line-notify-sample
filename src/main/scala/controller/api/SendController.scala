package controller.api

import controller.ApiController
import domain.user.User
import skinny.http._

/**
 * LINEにメッセージを送信するエンドポイント.
 */
class SendController extends ApiController {

  def sendMessage: String = {
    val userInfo = session.getAs[User]("UserInfo").getOrElse(throw new RuntimeException("UserInfo is empty."))
    val token = userInfo.token.getOrElse(new RuntimeException("token is empty."))

    val req = Request("https://notify-api.line.me/api/notify")
      .header("Content-Type", "application/x-www-form-urlencoded")
      .header("Authorization", s"Bearer $token")
      .formParams(
        "message" -> params.getAs[String]("msg").getOrElse("Empty...")
      )
    val response = HTTP.post(req)
    val result = if (response.status == 200) "" else response.textBody
    createJsonResult(result)
  }
}
