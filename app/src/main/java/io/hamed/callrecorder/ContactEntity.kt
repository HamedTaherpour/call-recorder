package io.hamed.callrecorder


/**
 * Author: Hamed Taherpour
 * *
 * Created: 10/23/2019
 */
class ContactEntity {

    var id: Int = 0
    var phone: String? = null
    var name: String? = null
    var description: String? = null
    var favorite: Boolean = false
    var incoming: Boolean = false
    var date: String? = null

    constructor(
        id: Int,
        phone: String?,
        name: String?,
        description: String?,
        favorite: Boolean,
        incoming: Boolean,
        date: String?
    ) {
        this.id = id
        this.phone = phone
        this.name = name
        this.description = description
        this.favorite = favorite
        this.incoming = incoming
        this.date = date
    }


}