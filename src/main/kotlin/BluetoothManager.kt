import org.sputnikdev.bluetooth.URL
import org.sputnikdev.bluetooth.manager.CharacteristicGovernor
import org.sputnikdev.bluetooth.manager.impl.BluetoothManagerBuilder

object BluetoothManagerSimpleTest {
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {

        BluetoothManagerBuilder()
            .withBlueGigaTransport("^*.$")
            .build()
            .getCharacteristicGovernor(
                URL(
                    "/85:54:1f:00:ed:d9/F7:EC:62:B9:CF:1F/"
                            + "0000180f-0000-1000-8000-00805f9b34fb/00002a19-0000-1000-8000-00805f9b34fb"
                ), true
            )
            .whenReady { obj: CharacteristicGovernor -> obj.read() }
            .thenAccept { data: ByteArray ->
                println(
                    "Battery level: " + data[0]
                )
            }.get()
    }
}