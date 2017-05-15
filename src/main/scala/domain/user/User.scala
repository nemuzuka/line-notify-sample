package domain.user

case class User(id: String, name: String, loginId: String, token: Option[String])
