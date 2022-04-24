package id.deval.tebu.db.response

import com.google.gson.annotations.SerializedName

data class UserResponseObject<T>(
    @field:SerializedName("User")
    var user:T
)
