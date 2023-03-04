package com.victor.adasfeed.passandodados

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

private fun String?.orEmptyString() = this ?: ""

@Parcelize
data class User(
    val userName: String,
    val userNickname: String,
    val imageUser: Int,
    val tel: String? = null,
) : Parcelable
//
//data class User1(
//    val userName: String,
//    val userNickname: String,
//    val telefone: Int,
//    val contato: Contato,
//    val imageUser: Int,
//) : Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString().orEmpty(),
//        parcel.readString().orEmpty(),
//        parcel.readInt(),
//        parcel.read ?: Contato(""),
//        parcel.readInt()
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(userName)
//        parcel.writeString(userNickname)
//        parcel.writeInt(telefone)
//        parcel.writeInt(imageUser)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<User1> {
//        override fun createFromParcel(parcel: Parcel): User1 {
//            return User1(parcel)
//        }
//
//        override fun newArray(size: Int): Array<User1?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
//
//data class Contato(
//    val name: String,
//) : Parcelable {
//    constructor(parcel: Parcel) : this(parcel.readString().orEmpty()) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(name)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Contato> {
//        override fun createFromParcel(parcel: Parcel): Contato {
//            return Contato(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Contato?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
