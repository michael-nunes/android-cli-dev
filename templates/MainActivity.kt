package $PKG_ID

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
	private lateinit var webView:WebView
	override fun onCreate(savedInstaceState: Bundle?) {
		super.onCreate(savedInstaceState)
		webView = WebView(this)
		setContent(webView)
		with(webView.settings) {
			javascriptEnabled = true
			domStorageEnabled = true
			allowFileAccess = true
		}
		webView.addJavascriptInterface(JsInterface(),"Android")
		webView.loadUrl("file:///android_assets/index.html")
	}
	inner class JsInterface {
		@JavascriptInterface
		fun scheduleReminder(dalayMs: Long, title:String, message:String, bigText:String?) {
			val intent = Intent(this@MainActivity,ReminderReceiver::class.java).apply {
				putExtra("title", title)
				putExtra("message", message)
				putExtra("bigText", bigText)
			}
			
		}
	}
}

