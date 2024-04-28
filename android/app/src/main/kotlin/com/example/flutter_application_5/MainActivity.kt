package com.example.myapp
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import com.example.myapp.kmm.processMethodChannel

class MainActivity: FlutterActivity() {

      override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger, "/sdk"
        ).setMethodCallHandler { call, result ->
            val methodName = call.method
            val methodArgs: Any = call.arguments ?: emptyList<Any>()
            if (methodName != null) {
                processMethodChannel(
                    methodName,
                    methodArgs,
                    { s -> result.success(s) },
                    { e, e1, e2 -> result.error(e, e1, e2) }
                )
            } else {
                // Handle the case where methodName is null
                result.error("method_name_null", "Method name is null", null)
            }
        }
    }
}
