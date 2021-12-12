package com.saquib.gmaildrawer2.Model

import com.saquib.gmaildrawer2.Model.ResponseModel.LabeledLatLng
import com.saquib.gmaildrawer2.Model.ResponseModel.Contact
import com.saquib.gmaildrawer2.Model.ResponseModel.BeenHere
import com.saquib.gmaildrawer2.Model.ResponseModel.VenuePage
import com.saquib.gmaildrawer2.Model.ResponseModel.HereNow
import com.saquib.gmaildrawer2.Model.ResponseModel.VenueChain
import com.saquib.gmaildrawer2.Model.ResponseModel.Delivery
import com.saquib.gmaildrawer2.Model.ResponseModel.Venue
import com.saquib.gmaildrawer2.Model.ResponseModel.Meta

class ResponseModel {
    inner class Meta {
        var code = 0
        var requestId: String? = null
    }

    inner class Item {
        var unreadCount = 0
    }

    inner class Notification {
        var type: String? = null
        var item: Item? = null
    }

    inner class Contact {
        var phone: String? = null
        var formattedPhone: String? = null
        var twitter: String? = null
        var instagram: String? = null
        var facebook: String? = null
        var facebookUsername: String? = null
        var facebookName: String? = null
    }

    inner class LabeledLatLng {
        var label: String? = null
        var lat = 0.0
        var lng = 0.0
    }

    inner class Location {
        var address: String? = null
        var crossStreet: String? = null
        var lat = 0.0
        var lng = 0.0
        var labeledLatLngs: List<LabeledLatLng>? = null
        var distance = 0
        var postalCode: String? = null
        var cc: String? = null
        var city: String? = null
        var state: String? = null
        var country: String? = null
        var formattedAddress: List<String>? = null
        var neighborhood: String? = null
    }

    inner class Icon {
        var prefix: String? = null
        var suffix: String? = null

        //public List<int> sizes;
        var name: String? = null
    }

    inner class Category {
        var id: String? = null
        var name: String? = null
        var pluralName: String? = null
        var shortName: String? = null
        var icon: Icon? = null
        var primary = false
    }

    inner class Stats {
        var tipCount = 0
        var usersCount = 0
        var checkinsCount = 0
    }

    inner class BeenHere {
        var lastCheckinExpiredAt = 0
    }

    inner class VenuePage {
        var id: String? = null
    }

    inner class HereNow {
        var count = 0
        var summary: String? = null
        var groups: List<Any>? = null
    }

    inner class VenueChain {
        var id: String? = null
    }

    inner class Provider {
        var name: String? = null
        var icon: Icon? = null
    }

    inner class Delivery {
        var id: String? = null
        var url: String? = null
        var provider: Provider? = null
    }

    inner class Menu {
        var type: String? = null
        var label: String? = null
        var anchor: String? = null
        var url: String? = null
        var mobileUrl: String? = null
        var externalUrl: String? = null
    }

    class Venue(var id: String, var name: String) {
        var contact: Contact? = null
        var location: Location? = null
        var categories: List<Category>? = null
        var verified = false
        var stats: Stats? = null
        var beenHere: BeenHere? = null
        var venuePage: VenuePage? = null
        var hereNow: HereNow? = null
        var referralId: String? = null
        var venueChains: List<VenueChain>? = null
        var hasPerk = false
        var url: String? = null
        var venueRatingBlacklisted = false
        var storeId: String? = null
        var allowMenuUrlEdit = false
        var hasMenu = false
        var delivery: Delivery? = null
        var menu: Menu? = null
    }

    inner class Response {
        var venues: List<Venue>? = null
        var confident = false
    }

    inner class Root {
        var meta: Meta? = null
        var notifications: List<Notification>? = null
        var response: Response? = null
    }
}