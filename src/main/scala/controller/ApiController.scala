package controller

import skinny._

/**
 * The base controller for API endpoints.
 */
trait ApiController extends SkinnyApiController {

  /*
   * Handles when unexpected exceptions are thrown from controllers.
   */
  addErrorFilter {
    case e: Throwable =>
      // For example, logs a exception and responds with status 500.
      logger.error(e.getMessage, e)
      haltWithBody(500)
  }

  //共通処理
  beforeAction() {
    contentType = "application/json"
  }

  /**
   * JSON戻り値生成.
   * @param result 戻り値
   * @return JSON形式の戻り値
   */
  def createJsonResult(result: AnyRef): String = {
    toJSONString(JsonResult(
      msgs = Seq(),
      errorMsg = Map(),
      result = result
    ), underscoreKeys = false)
  }
}
