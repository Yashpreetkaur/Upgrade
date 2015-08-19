ant clean
ant build
android create uitest-project -n src -t 12 -p .
adb push ./bin/src.jar /data/local/tmp/
adb push ./local.properties /data/local/tmp/

#adb push /Users/yashpreet/Documents/builds/android-client-3.9.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.9.2.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.9.6.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.9.7.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.9.8.apk /data/local/tmp/
adb push /Users/yashpreet/Documents/builds/android-client-3.9.8.81.apk /data/local/tmp/
adb push /Users/yashpreet/Documents/builds/android-client-3.9.8.82.apk /data/local/tmp/
adb push /Users/yashpreet/Documents/builds/android-client-3.9.9.apk /data/local/tmp/
adb push /Users/yashpreet/Documents/builds/android-client-3.9.9.81.apk /data/local/tmp/

adb push /Users/yashpreet/Documents/builds/new/android-client-3.9.9.82.apk /data/local/tmp/

adb shell uiautomator runtest src.jar -c com.bsb.hike.update_test.UpdateVersionTests
#adb shell uiautomator runtest src.jar -c com.bsb.hike.update_test.UpdateResetTests



#3.5.0 (TVC)
#3.5.1 (NUX)
#3.6.0 (backup)
#3.6.6 (backlog)
#3.7.0 (voip)
#3.7.8 (platform nux analytics)
#3.8.0