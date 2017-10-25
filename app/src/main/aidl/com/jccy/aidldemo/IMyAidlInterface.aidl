// IMyAidlInterface.aidl
package com.jccy.aidldemo;
// Declare any non-default types here with import statements
import com.jccy.aidldemo.User;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    String getName();
    User getUser();
    void addUser(in User user);
}
