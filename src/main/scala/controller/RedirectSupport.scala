package controller

import javax.servlet.http.HttpServletRequest

/**
 * リダイレクトに関する共通処理.
 */
trait RedirectSupport {
  /**
   * LINE用リダイレクトURL作成.
   * LINE認証が成功した後に呼ばれるアプリケーションのエンドポイント文字列を生成します
   * @param request HttpServletRequest
   * @return リダイレクト用URL
   */
  protected[this] def createRedirectForLineUri(request: HttpServletRequest): String = {
    val requestURL = request.getRequestURL
    val servletPath = request.getServletPath
    val index = requestURL.lastIndexOf(servletPath)
    requestURL.substring(0, index + 1) + "api/_created-code"
  }
}
