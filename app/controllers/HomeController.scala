package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(<h1>Hello World!</h1>).as(HTML).withHeaders(
      CACHE_CONTROL -> "max-age=3600",
      EXPIRES -> "Expires: Wed, 21 Oct 2020 07:28:00 GMT"
    ).withCookies(
      Cookie("colour", "blue")
    ).withSession("connected" -> "user@gmail.com"
    )
  }

  def flash() = Action { implicit request =>
    Ok{
      request.flash.get("success").getOrElse("Welcome!")
    }
  }

  def help() = Action {
    Redirect("/flash").flashing("success" -> "You have been successfully redirected")
  }

  def infinite = Action {
    Redirect("/infinite")
  }

}
