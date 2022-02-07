package com.techjays.techjayscountrycodepicker.api

interface ResponseListener {

    /**
     * @param r - The model class that is passed on the parser
     */
    fun onResponse(r: Response?)
}
