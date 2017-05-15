package controller

import skinny._
import skinny.controller.AssetsController
import skinny.micro.routing.Route

object Controllers {

  def mount(ctx: ServletContext): Unit = {
    lineAuthorize.mount(ctx)
    lineAuthorizedApi.mount(ctx)
    loginApi.mount(ctx)
    sendApi.mount(ctx)
    setting.mount(ctx)
    root.mount(ctx)
    AssetsController.mount(ctx)
  }

  object root extends RootController with Routes {
    val indexUrl: Route = get("/?")(index).as('index)
  }

  object setting extends SettingController with Routes {
    val indexUrl: Route = get("/setting")(index).as('index)
  }

  object lineAuthorize extends LineAuthorizeController with Routes {
    val authorizeUrl: Route = get("/line/authorize")(authorize).as('authorize)
  }

  object lineAuthorizedApi extends _root_.controller.api.AuthorizeController with Routes {
    val createdCodeUrl: Route = post("/api/_created-code")(createdCode).as('createdCode)
  }

  object loginApi extends _root_.controller.api.LoginController with Routes {
    val executeUrl: Route = post("/api/_login")(execute).as('execute)
  }

  object sendApi extends _root_.controller.api.SendController with Routes {
    val sendUrl: Route = post("/api/_send-message")(sendMessage).as('sendMessage)
  }

}
