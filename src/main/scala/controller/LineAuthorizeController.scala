package controller

import util.ConfigHelper

/**
 * LINE Notify用のtoken取得用.
 */
class LineAuthorizeController extends ApplicationController with RedirectSupport {

  def authorize = {
    val url = s"https://notify-bot.line.me/oauth/authorize?" +
      s"response_type=code&" +
      s"client_id=${ConfigHelper.lineClientId}&" +
      s"redirect_uri=${createRedirectForLineUri(request)}&" +
      s"scope=notify&" +
      s"state=${java.util.UUID.randomUUID.toString}&" +
      s"response_mode=form_post"
    redirect(url)
  }
}
