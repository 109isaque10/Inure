package app.simple.inure.dialogs.apks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import app.simple.inure.R
import app.simple.inure.decorations.ripple.DynamicRippleTextView
import app.simple.inure.decorations.switchview.SwitchView
import app.simple.inure.extensions.fragments.ScopedBottomSheetFragment
import app.simple.inure.preferences.ApkBrowserPreferences

class ApksMenu : ScopedBottomSheetFragment() {

    private lateinit var loadSplitIconSwitch: SwitchView
    private lateinit var openSettings: DynamicRippleTextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_menu_apk_browser, container, false)

        loadSplitIconSwitch = view.findViewById(R.id.load_split_icon)
        openSettings = view.findViewById(R.id.dialog_open_apps_settings)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadSplitIconSwitch.setChecked(ApkBrowserPreferences.isLoadSplitIcon())

        loadSplitIconSwitch.setOnSwitchCheckedChangeListener { isChecked ->
            ApkBrowserPreferences.setLoadSplitIcon(isChecked)
        }

        openSettings.setOnClickListener {
            openSettings()
        }
    }

    companion object {
        fun newInstance(): ApksMenu {
            val args = Bundle()
            val fragment = ApksMenu()
            fragment.arguments = args
            return fragment
        }

        fun FragmentManager.showApksMenu(): ApksMenu {
            val dialog = newInstance()
            dialog.show(this, ApksMenu::class.java.simpleName)
            return dialog
        }
    }
}