# paste this in build.gradle(module)

    implementation 'com.github.dev-iderize:countrycodepicker:0.2'

# countrycodepicker

Country code picker is used to bring up a bottom sheet dialog for picking up a country code with the
flag and country name

# for click action

    fun onTestClicked() {
        openCountryDialog(BASE_URL)
    }

# for opening the country code dialog

    private fun openCountryDialog(url: String) {
        val dialogFragment = CountryPickerDialog.newInstance(url)
        dialogFragment.show(
            context.supportFragmentManager,
            "Country Picker dialog"
        )

    }

# Country code along wih other details and image is broadcasted from the adapter like

          intent.putExtra("countryName", eachListData.mName)
            intent.putExtra("image", image)
            intent.putExtra("countryCode", eachListData.mCountryCode)
            intent.putExtra("countryShortCode", eachListData.mShortcode)

#Authors 
    Techjays Android Team 07-02-2022
