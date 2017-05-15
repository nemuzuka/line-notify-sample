package controller.api

import controller.{ ApiController, RedirectSupport }
import domain.user.User
import entity.LineToken
import skinny.http._
import util.ConfigHelper

import scala.util.{ Failure, Success }

/**
 * LINEの認証が完了した後に呼ばれるアクセスポイント.
 */
class AuthorizeController extends ApiController with RedirectSupport {

  def createdCode = {
    for {
      code <- params.getAs[String]("code")
    } yield {
      setTokenOnSession(code)
    }
    redirect("/setting")
  }

  private[this] def setTokenOnSession(code: String): Unit = {

    val req = Request("https://notify-bot.line.me/oauth/token")
      .header("Content-Type", "application/x-www-form-urlencoded")
      .formParams(
        "grant_type" -> "authorization_code",
        "code" -> code,
        "redirect_uri" -> createRedirectForLineUri(request),
        "client_id" -> ConfigHelper.lineClientId,
        "client_secret" -> ConfigHelper.lineClientSecret
      )
    val response = HTTP.post(req)
    val accessToken = if (200 == response.status) getAccessToken(response.textBody) else None
    session.getAs[User]("UserInfo") foreach { userInfo =>
      session += "UserInfo" -> userInfo.copy(token = accessToken)
    }
  }

  private[this] def getAccessToken(body: String): Option[String] = {
    fromJSONString[LineToken](body, underscoreKeys = false, asIs = false) match {
      case Success(v) =>
        Option(v.accessToken)
      case Failure(error) =>
        logger.error(error.getMessage, error)
        None
    }
  }
}
