//Coded by ateeq72@xd.0a")
//Thanks to jrbenito for UDP stuff @ https://github.com/jrbenito/Android-UDP-Terminal
//Thanks to zerokol for JoyStickView!
//anyone can use the code but please provide proper credits! Thanks :)
package com.ahmed.ateeqsrpicar;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.zerokol.views.JoystickView;
import com.zerokol.views.JoystickView.OnJoystickMoveListener;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AteeqRPiActivity extends Activity {
	
	private TextView angleTextView;
	private TextView powerTextView;
	private TextView directionTextView;
	private Button but_lset;

	//porting as others views
	private JoystickView joystickAcc;
	private JoystickView joystickStr;

	private static String ip;
	private static int port;
	private static int lport;
	private static String payload;

	public List<DataClass> data;

	private int sendPacket(String payload) throws IOException {
		AteeqRPiActivity.payload = payload;
        new moveBot().execute();
		return 0;
	}
	final DatabaseHandler db = new DatabaseHandler(this);

	private void getData()
	{
		data = db.getAllData();
		for (DataClass cn : data) {
			String log = "IP: " + cn.getIpHost() + " ,LPORT: " + cn.getlPorta() + " ,PORT " + cn.getPort();
			ip = cn.getIpHost();
			port = cn.getPort();
			lport = cn.getlPorta();
			Toast.makeText(this, "Current Settings :\nIP : " + ip + "\nPORT : " + port + "\nL-PORT : " + lport, Toast.LENGTH_SHORT).show();
			// Writing Contacts to log
			Log.d("Name: ", log);
		}
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        try {
            getData();
		}
		catch (Exception e)
		{
			String log = " Error : " + e;
			Log.d("", log);
			Toast.makeText(this,"Error Occured : "+e,Toast.LENGTH_SHORT).show();
		}

        angleTextView = (TextView) findViewById(R.id.angleTextView);
        powerTextView = (TextView) findViewById(R.id.powerTextView);
        directionTextView = (TextView) findViewById(R.id.directionTextView);

        
        // referring as others views
        joystickAcc = (JoystickView) findViewById(R.id.joystickViewAcc);
		joystickStr = (JoystickView) findViewById(R.id.joystickViewSteer);

        joystickAcc.setOnJoystickMoveListener(new OnJoystickMoveListener() {
			@Override
			public void onValueChanged(int angle, int power, int direction) {
				angleTextView.setText(" " + String.valueOf(angle) + "°");
				powerTextView.setText(" " + String.valueOf(power) + "%");
				switch (direction) {
					case JoystickView.FRONT:
						directionTextView.setText(R.string.front_lab);

						try {

							sendPacket("0;1");

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							break;
						}

					case JoystickView.FRONT_RIGHT:
						directionTextView.setText(R.string.left_front_lab);

						try {
							sendPacket("-15;1");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case JoystickView.RIGHT:
						directionTextView.setText(R.string.left_lab);

						try {

							sendPacket("-15;0");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case JoystickView.RIGHT_BOTTOM:
						directionTextView.setText(R.string.bottom_left_lab);

						try {

							sendPacket("-15;2");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case JoystickView.BOTTOM:
						directionTextView.setText(R.string.bottom_lab);

						try {

							sendPacket("0;2");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case JoystickView.BOTTOM_LEFT:
						directionTextView.setText(R.string.right_bottom_lab);

						try {
							sendPacket("15;2");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case JoystickView.LEFT:
						directionTextView.setText(R.string.right_lab);

						try {
							sendPacket("15;0");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case JoystickView.LEFT_FRONT:
						directionTextView.setText(R.string.front_right_lab);

						try {

							sendPacket("15;1");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					default:
						directionTextView.setText(R.string.center_lab);

						try {

							sendPacket("0;0");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		}, JoystickView.DEFAULT_LOOP_INTERVAL);

		but_lset = (Button) findViewById(R.id.settings_btn);

		but_lset.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getBaseContext(), SettingsConDetails.class));
			}
		});
        }

	private static class moveBot extends AsyncTask<String, String, Integer>
	{
		String ipHost = AteeqRPiActivity.ip;
		int lPorta = AteeqRPiActivity.lport;
		int port = AteeqRPiActivity.port;
		String payload = AteeqRPiActivity.payload;


		@Override
		protected Integer doInBackground(String... params) {

			try {
				InetAddress address = null;
				address = InetAddress.getByName(ipHost);
				DatagramSocket socket = new DatagramSocket(lPorta);
				DatagramPacket packet = new DatagramPacket(payload.getBytes(),payload.length(),address,port);
				socket.send(packet);
				socket.disconnect();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 0;
		}
	}

    }

