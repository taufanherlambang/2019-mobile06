package id.ac.polinema.idealbodyweight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import id.ac.polinema.idealbodyweight.fragment.AboutFragment;
import id.ac.polinema.idealbodyweight.fragment.BmiFragment;
import id.ac.polinema.idealbodyweight.fragment.BrocaIndexFragment;
import id.ac.polinema.idealbodyweight.fragment.MenuFragment;
import id.ac.polinema.idealbodyweight.fragment.ResultFragment;

public class MainActivity extends AppCompatActivity implements
		MenuFragment.OnFragmentInteractionListener,
		BrocaIndexFragment.OnFragmentInteractionListener,
		ResultFragment.OnFragmentInteractionListener,
		BmiFragment.OnFragmentInteractionListener{

	// Deklarasikan atribut Fragment di sini
	private AboutFragment aboutFragment;
	private MenuFragment menuFragment;
	private BrocaIndexFragment brocaIndexFragment;
	private BmiFragment bmiFragment;
	private ResultFragment resultFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		menuFragment = new MenuFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, menuFragment)
				.commit();
		aboutFragment = AboutFragment.newInstance("Alenovan Wiradhika Putra");
		resultFragment = new ResultFragment();
		brocaIndexFragment = new BrocaIndexFragment();
		bmiFragment = new BmiFragment();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return  true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		// TODO: Tambahkan penanganan menu di sini
		if (item.getItemId() == R.id.menu_about) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, aboutFragment)
					.addToBackStack(null)
					.commit();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onFragmentInteraction(Uri uri) {

	}

	@Override
	public void onCalculateBmiIndexClicked(float index) {
		resultFragment.setInformation("The Body Mass Index (BMI) is   "+Math.round(index)+"kg/m2");
		resultFragment.setStatus("1");
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, resultFragment)
				.commit();
	}

	@Override
	public void onTryAgainButtonClicked(String tag) {
//		Toast.makeText(getApplicationContext(), tag, Toast.LENGTH_SHORT).show();

		if(tag.equalsIgnoreCase("2")) {
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, brocaIndexFragment)
					.commit();
		}else if(tag.equalsIgnoreCase("1")){
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.fragment_container, bmiFragment)
					.commit();
		}

	}

	@Override
	public void onCalculateBrocaIndexClicked(float index) {
		resultFragment.setInformation(String.format("BROCA : Your ideal weight is %.2f kg", index));
		resultFragment.setStatus("2");
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, resultFragment)
				.commit();
	}

	@Override
	public void onBrocaIndexButtonClicked() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, brocaIndexFragment)
				.commit();
	}

	@Override
	public void onBodyMassIndexButtonClicked() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, bmiFragment)
				.commit();
	}


}
