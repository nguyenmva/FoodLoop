package com.example.foodloop;
public class WhatWasDone {
}
/*
2026/04/02
-Reduced the user country options to only Canada and USA, assuming FoodLoop only operates in these two places.
-Redid DB auto-entries for the new Requests table.
-Moved the code for populating the recycler view in ActiveDonations to its own method, outside of onCreate. This is to call the method to "refresh" the list without having to use onCreate.
-Commented out the hardcoded data in ActiveDonations. Added donations in the DB for everyone.
-Updated ActiveRequests to show only what has been requested, not what has been donated. Status isn't updated anymore. GiaDonateAdapter needs to be updated to write status changes to the DB.
-Moved the Remember Me function to another sharedPreference to solve a bug. We're reading the email from the the sharedPreference to see who's logged in. If Remember Me is never checked, nothing gets saved/updated to sharedPreferences. Then either "no one" is logged in or the last email saved is still logged in. Now email and password are always saved and checkbox state is saved separately.
-Deleted AdapterAccounts.java, it's not needed.















 */