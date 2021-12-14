package com.kseyko.navigationdeeplinkwidgetsample

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      12,December,2021      ║
╚════════════════════════════╝
 */
class WidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val remoteViews= RemoteViews(context.packageName,R.layout.deep_link_widget_layout)

        val args = Bundle()
        args.putString("forgetpassword", "From Widget")

        val settingsIntent = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.navigation_forget_password)
            .setArguments(args)
            .createPendingIntent()


        val signupIntent = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.mobile_navigation)
            .setDestination(R.id.navigation_sign_up)
            .createPendingIntent()

        remoteViews.setOnClickPendingIntent(R.id.widget_forget_password,settingsIntent)
        remoteViews.setOnClickPendingIntent(R.id.widget_signup,signupIntent)

        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews)
    }
}