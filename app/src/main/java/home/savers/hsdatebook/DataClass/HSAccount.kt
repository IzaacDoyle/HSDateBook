package home.savers.hsdatebook.DataClass

data class HSAccount(
    var usernames: String,
    var staffNames: String,
){
    constructor():this("","")
}