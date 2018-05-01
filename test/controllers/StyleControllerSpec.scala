import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._


@RunWith(classOf[JUnitRunner])
class StyleControllerSpec extends Specification {
	"StyleControllerSpec" should {
		"list styles" in new WithApplication {
			val styles = route(app, FakeRequest(GET, "/styles/list")).get

			status(styles) must equalTo(OK)

			contentType(styles) must beSome.which(_ == "application/json")

			Some(contentAsString(styles)) must beSome(contain("新中式"))

		}
	}
}
