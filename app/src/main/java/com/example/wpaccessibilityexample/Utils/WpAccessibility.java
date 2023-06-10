package com.example.wpaccessibilityexample.Utils;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class WpAccessibility extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d("======>", "onAccessibilityEvent: " );

        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            if (event.getPackageName().equals("com.whatsapp")  ||  event.getPackageName().equals("com.whatsapp.w4b") ) {
                Toast.makeText(this, "WhatsApp is opened", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onServiceConnected() {
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        accessibilityServiceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        accessibilityServiceInfo.flags = AccessibilityServiceInfo.DEFAULT;
        setServiceInfo(accessibilityServiceInfo);
    }


    @Override
    public void onInterrupt() {
        Log.d("======>", "onInterrupt: ");
    }
}
