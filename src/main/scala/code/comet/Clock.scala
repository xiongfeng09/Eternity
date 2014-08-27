package code
package comet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util.TimeHelpers.now
import net.liftweb.util.Schedule
import net.liftweb.common._
import java.util.Date
import code.lib._
import net.liftweb.http.CometActor
import net.liftweb.http.js.JsCmds.SetHtml

class Clock extends CometActor {
  override def defaultPrefix = Full("clk")
  
  def render = "#time *" replaceWith now.toString
  
  // schedule a ping every 10 seconds so we redraw
  Schedule.schedule(this, Tick, 1000L)
  override def lowPriority : PartialFunction[Any, Unit] = {
    case Tick => {
      partialUpdate {
        reRender(false)
      }
      // schedule an update in 10 seconds
      Schedule.schedule(this, Tick, 1000L) 
    }
  }
}
case object Tick


