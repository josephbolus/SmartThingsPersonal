/**

 *  Spark Core

 *

 *  Author: Justin Wurth

 *  Date: 2014-09-19

 */

 

 preferences {

    input("deviceId", "text", title: "Device ID")

    input("token", "text", title: "Access Token")

}

 

 // for the UI

metadata {

    // Automatically generated. Make future change here.

    definition (name: "Particle Photon Blinds", author: "justinwurth@gmail.com") {
		
        capability "Switch Level"
        capability "Switch"

    }


    // tile definitions

    tiles {
		standardTile("switch", "device.switch", width: 2, height: 2, canChangeIcon: true) {
/*			state "on", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#79b821", nextState:"turningOff"
			state "off", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff", nextState:"turningOn"
			state "turningOn", label:'${name}', icon:"st.switches.switch.on", backgroundColor:"#79b821"
			state "turningOff", label:'${name}', icon:"st.switches.switch.off", backgroundColor:"#ffffff"
*/

			state "on", label:'${name}', action:"switch.off", icon:"st.switches.switch.on", backgroundColor:"#79b821"
			state "off", label:'${name}', action:"switch.on", icon:"st.switches.switch.off", backgroundColor:"#ffffff"

		}
		
		standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
			state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
		}
		/*
        controlTile("levelSliderControl", "device.level", "slider", height: 1, width: 3, inactiveLabel: false) {
			state "level", action:"switch level.setLevel"
		}
		*/
		main(["switch"])
		details(["switch"])
	}


}


def parse(String description) {

    log.error "This device does not support incoming events"

    return null

}


def on() {

	log.debug "On Cmd"
    put '1'

}


def off() {

		log.debug "Off Cmd"
    put '0'

}

/*
def setLevel(value) {
    def level = Math.min(value as Integer, 99)
    put value
}
*/

private put(led) {

    //Spark Core API Call

log.debug "${deviceId}"
log.debug "https://api.particle.io/v1/devices/${deviceId}/blindControl"
    httpPost(

        uri: "https://api.particle.io/v1/devices/${deviceId}/blindControl",

        body: [access_token: token, command: led],  

    ) {response -> log.debug (response.data)}

}