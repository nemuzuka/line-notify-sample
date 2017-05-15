package controller

case class JsonResult(
  msgs: Seq[String],
  errorMsg: Map[String, Seq[String]],
  result: AnyRef
)