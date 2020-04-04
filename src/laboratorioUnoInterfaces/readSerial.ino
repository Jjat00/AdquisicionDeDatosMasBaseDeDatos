/*
 * Archivo: ReadSerial.ino
 * Fecha creacion: 17 de febrereo de 2020
 * Fecha última modificacion: 18 de febrero de 2020
 * Autores: Jaimen Aza           
 * 				Juan Camilo Quintero    
 */

//asinar nombres y pines a cada una de las señales análogas
#define senalAnaloga0 A0
#define senalAnaloga1 A1
#define senalAnaloga2 A2
#define senalAnaloga3 A3
#define senalAnaloga4 A4
#define senalAnaloga5 A5
//asinar nombres y pines a cada una de las señales digitales
#define senalDigital0 2
#define senalDigital1 3
#define senalDigital2 4
#define senalDigital3 5
//asinar nombres y pines a cada una de las señales digitales de salida
#define senalDigitalDeSalida0 8
#define senalDigitalDeSalida1 9
#define senalDigitalDeSalida2 10
#define senalDigitalDeSalida3 11
// iniciallizar cada una de las variables
int estadoFsm = 0;
int contadorFSM = 0;
char bufferFSM[8];
char header = 'U';
int canalAnalogoSeleccionado;
int canalDigitalSeleccionado;
int canalSalidaDigitalSeleccionado;
int tiempoMuestreo;
int estadoSenalSalidaDigital;
// the setup function runs once when you press reset or power the board
void setup()
{
    Serial.begin(9600);
    Serial.println("Inicializando...");
    //configurar como entrada los pines de las señales analogas
    pinMode(senalAnaloga0, INPUT);
    pinMode(senalAnaloga1, INPUT);
    pinMode(senalAnaloga2, INPUT);
    pinMode(senalAnaloga3, INPUT);
    pinMode(senalAnaloga4, INPUT);
    pinMode(senalAnaloga5, INPUT);
    //configurar como entrada los pines de las señales digitales
    pinMode(senalDigital0, INPUT);
    pinMode(senalDigital1, INPUT);
    pinMode(senalDigital2, INPUT);
    pinMode(senalDigital3, INPUT);
    //configurar como salida los pines de las señales digitales
    pinMode(senalDigitalDeSalida0, OUTPUT);
    pinMode(senalDigitalDeSalida1, OUTPUT);
    pinMode(senalDigitalDeSalida2, OUTPUT);
    pinMode(senalDigitalDeSalida3, OUTPUT);
}

// the loop function runs over and over again forever
void loop()
{
    leerDatos();
    enviarDatos();
    activarSenales();
    delay(tiempoMuestreo);
}


//igual el datos de la señal analogica para que siempre tenga la misma longitud
String igualarTamanoString(int datoEntrada)
{
    String datoSalida = "0000";

    if (datoEntrada >= 1000)
    {
        datoSalida = (String)datoEntrada;
    }
    else if (datoEntrada < 10)
    {
        datoSalida = "000" + (String)datoEntrada;
    }
    else if (datoEntrada < 100)
    {
        datoSalida = "00" + (String)datoEntrada;
    }
    else if (datoEntrada < 1000)
    {
        datoSalida = "0" + (String)datoEntrada;
    }
    return datoSalida;
}

//envia los datos segun la configuracion que el usuario pidio
void enviarDatos()
{
    int lecturaSerialAnaloga = 0;
    int lecturaSerialDigital = 0;

        if (canalDigitalSeleccionado == 0)
        {
            lecturaSerialDigital = digitalRead(senalDigital0);
        }
        if (canalDigitalSeleccionado == 1)
        {
            lecturaSerialDigital = digitalRead(senalDigital1);
        }
        if (canalDigitalSeleccionado == 2)
        {
            lecturaSerialDigital = digitalRead(senalDigital2);
        }
        if (canalDigitalSeleccionado == 3)
        {
            lecturaSerialDigital = digitalRead(senalDigital3);
        }
        
        if (canalAnalogoSeleccionado == 0)
        {
            lecturaSerialAnaloga = analogRead(senalAnaloga0);
        }
        else if (canalAnalogoSeleccionado == 1)
        {
            lecturaSerialAnaloga = analogRead(senalAnaloga1);
        }
        else if (canalAnalogoSeleccionado == 2)
        {
            lecturaSerialAnaloga = analogRead(senalAnaloga2);
        }
        else if (canalAnalogoSeleccionado == 3)
        {
            lecturaSerialAnaloga = analogRead(senalAnaloga3);
        }
        else if (canalAnalogoSeleccionado == 4)
        {
            lecturaSerialAnaloga = analogRead(senalAnaloga4);
        }
        else if (canalAnalogoSeleccionado == 5)
        {
            lecturaSerialAnaloga = analogRead(senalAnaloga0);
        }
        Serial.print("UUU" + (String)lecturaSerialDigital + igualarTamanoString(lecturaSerialAnaloga));
}

//activa las salidas digitales que el usuario desee
void activarSenales(){
        if (canalSalidaDigitalSeleccionado == 0)
        {
            if (estadoSenalSalidaDigital == 0)
            {
                digitalWrite(senalDigitalDeSalida0, LOW);
            }
            if (estadoSenalSalidaDigital == 1)
            {
                digitalWrite(senalDigitalDeSalida0, HIGH);
            }
            digitalWrite(senalDigitalDeSalida1, LOW);
            digitalWrite(senalDigitalDeSalida2, LOW);
            digitalWrite(senalDigitalDeSalida3, LOW);
        }
        if (canalSalidaDigitalSeleccionado == 1)
        {
            if (estadoSenalSalidaDigital == 0)
            {
                digitalWrite(senalDigitalDeSalida1, LOW);
            }
            if (estadoSenalSalidaDigital == 1)
            {
                digitalWrite(senalDigitalDeSalida1, HIGH);
            }
            digitalWrite(senalDigitalDeSalida0, LOW);
            digitalWrite(senalDigitalDeSalida2, LOW);
            digitalWrite(senalDigitalDeSalida3, LOW);
        }
        if (canalSalidaDigitalSeleccionado == 2)
        {
            if (estadoSenalSalidaDigital == 0)
            {
                digitalWrite(senalDigitalDeSalida2, LOW);
            }
            if (estadoSenalSalidaDigital == 1)
            {
                digitalWrite(senalDigitalDeSalida2, HIGH);
            }
            digitalWrite(senalDigitalDeSalida0, LOW);
            digitalWrite(senalDigitalDeSalida1, LOW);
            digitalWrite(senalDigitalDeSalida3, LOW);
        }
        if (canalSalidaDigitalSeleccionado == 3)
        {
            if (estadoSenalSalidaDigital == 0)
            {
                digitalWrite(senalDigitalDeSalida3, LOW);
            }
            if (estadoSenalSalidaDigital == 1)
            {
                digitalWrite(senalDigitalDeSalida3, HIGH);
            }
            digitalWrite(senalDigitalDeSalida0, LOW);
            digitalWrite(senalDigitalDeSalida1, LOW);
            digitalWrite(senalDigitalDeSalida2, LOW);
        }
}

//lee la cadena enviada desde java por el usuario 
void leerDatos()
{
    while (Serial.available())
    {
        char data = (char)Serial.read();
        fsm(data);
    }
}

//fms para los detectar los datos recibidos 
void fsm(char trama)
{
    switch (estadoFsm)
    {
    case 0:
        if (trama == header)
        {
            estadoFsm = 1;
        }
        else
        {
            estadoFsm = 0;
        }
        break;
    case 1:
        if (trama == header)
        {
            estadoFsm = 2;
        }
        else
        {
            estadoFsm = 0;
        }
        break;
    case 2:
        if (trama == header)
        {
            estadoFsm = 3;
        }
        else
        {
            estadoFsm = 0;
        }
        break;
    case 3:
        if (contadorFSM < 7)
        {
            bufferFSM[contadorFSM] = trama;
            contadorFSM++;
        }
        if (contadorFSM == 7)
        {
            String str = String(bufferFSM);

            String cadenaAuxiliar0 = str.substring(0, 1);
            canalAnalogoSeleccionado = cadenaAuxiliar0.toInt();

            String cadenaAuxiliar1 = str.substring(1, 2);
            canalDigitalSeleccionado = cadenaAuxiliar1.toInt();

            String cadenaAuxiliar2 = str.substring(2, 3);
            canalSalidaDigitalSeleccionado = cadenaAuxiliar2.toInt();

            String cadenaAuxiliar3 = str.substring(3, 4);
            estadoSenalSalidaDigital = cadenaAuxiliar3.toInt();

            String cadenaAuxiliar4 = str.substring(4, 7);
            tiempoMuestreo = cadenaAuxiliar4.toInt();
            estadoFsm = 0;
            contadorFSM = 0;
            
            
        }
    default:
        break;
    }
}