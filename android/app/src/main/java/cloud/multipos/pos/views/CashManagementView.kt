/**
 * Copyright (C) 2023 multiPOS, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
 
package cloud.multipos.pos.views

import cloud.multipos.pos.R
import cloud.multipos.pos.Pos
import cloud.multipos.pos.controls.SessionManager
import cloud.multipos.pos.util.*
import cloud.multipos.pos.models.Ticket

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Button;
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat

class CashManagementView (sessionManager: SessionManager): PosLayout (Pos.app, null) {
	 	 
	 private lateinit var cashManagementContent: ViewGroup
	 private var currentView: View?
	 
	 init {

		  setLayoutParams (LinearLayout.LayoutParams (LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
		  Pos.app.inflater.inflate (R.layout.cash_management_layout, this)

		  currentView = null
		  
	 	  cashManagementContent = findViewById (R.id.cm_content) as LinearLayout
		  
		  replaceView (CashManagementCount (sessionManager, this))
		  
		  Pos.app.controlLayout.load (this)
  
	 }

	 fun replaceView (view : View) {
		  
		  if (currentView != null) {
				
				cashManagementContent.removeView (currentView)
		  }

		  currentView = view
		  cashManagementContent.addView (view);
	 }

	 fun updateCount () {
	 }
}
	 
