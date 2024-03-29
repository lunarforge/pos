/**
 * Copyright (C) 2023 multiPOS, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
package cloud.multipos.pos.controls

import cloud.multipos.pos.*
import cloud.multipos.pos.models.*
import cloud.multipos.pos.db.*
import cloud.multipos.pos.util.*
import cloud.multipos.pos.views.ConfirmView
import cloud.multipos.pos.views.ReportView

class ZSession (): SessionManager () {

	 init {
		  
		  confirmed (false)
	 }

	 override fun controlAction (jar: Jar) {

		  Logger.d ("z session... " + jar)

		  jar (Jar ())
		  if (jar != null) {
				
				jar (jar)
		  }

		  if (confirmed ()) {

				// confirmed (false)
				// super.controlAction (jar ())
				// complete (Ticket.Z_SESSION)

				// val timeStamp = Pos.app.db.timestamp (java.util.Date ())
				
				// Pos.app
				// 	 .ticket
		  		// 	 .put ("ticket_type", Ticket.Z_SESSION)
		  		// 	 .put ("state", Ticket.COMPLETE)
		  		// 	 .put ("star_time", timeStamp)
		  		// 	 .put ("complete_time", timeStamp)
				
				// Pos.app.db.exec ("update tickets set start_time = '" + timeStamp + "', " +
				// 							  "complete_time = '" + timeStamp + "', " +
				// 							  "ticket_type = " + Ticket.VOID + ", " +
				// 							  "state = " + Ticket.COMPLETE + " " +
				// 							  "where id = " + Pos.app.ticket.getInt ("id"))

		  		confirmed (false)
				setJar (Jar ().put ("print_report", true))
				super.controlAction (jar ())

				Pos.app.ticket.put ("ticket_type", Ticket.Z_SESSION)
				Pos.app.receiptBuilder ().ticket (Pos.app.ticket, PosConst.PRINTER_REPORT)
				ReportView (Pos.app.getString ("ticket"),
								Jar ()
		  							 .put ("report_title", Pos.app.getString ("z_report"))
		  							 .put ("report_text", Pos.app.receiptBuilder ().text ())
		  							 .put ("print_opt", true)
		  							 .put ("print_type", "report"))		

				complete (Ticket.Z_SESSION)
		  }
		  else {

				setJar (Jar ())
				ConfirmView (Pos.app.getString ("confirm_z_session"), this)
		  }
	 }
}
