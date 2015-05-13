ant build
android create uitest-project -n src -t 12 -p .
adb push ./bin/src.jar /data/local/tmp/
adb push ./local.properties /data/local/tmp/
adb push ./builds.properties /data/local/tmp/

#adb push /Users/yashpreet/Documents/builds/android-client-2.7.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-2.7.1.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-2.8.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-2.8.2.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-2.8.5.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-2.9.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-2.9.6.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.0.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.0.1.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.1.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.2.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.3.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.3.1.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.3.5.apk /data/local/tmp/

#adb push /Users/yashpreet/Documents/builds/android-client-3.5.0.apk /data/local/tmp/
 
#adb push /Users/yashpreet/Documents/builds/android-client-3.5.1.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.6.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.6.6.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.7.0.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.7.8.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.8.0.apk /data/local/tmp/

adb push /Users/yashpreet/Documents/builds/android-client-3.8.6.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.8.7.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.8.8.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.8.9.apk /data/local/tmp/
#adb push /Users/yashpreet/Documents/builds/android-client-3.9.0.apk /data/local/tmp/

adb push /Users/yashpreet/.jenkins/jobs/internal/workspace/build/outputs/apk/android-client-3.9.2.53.apk /data/local/tmp/

#adb shell uiautomator runtest src.jar -c com.bsb.hike.update_test.UpdateVersionTests
adb shell uiautomator runtest src.jar -c com.bsb.hike.update_test.UpdateResetTests



#3.5.0 (TVC)
#3.5.1 (NUX)

#3.6.0 (backup)
#3.6.6 (backlog)
#3.7.0 (voip)
#3.7.8 (platform nux analytics)
#3.8.0