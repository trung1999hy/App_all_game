package com.example.manygameinapplication.ui.fragment.main

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.cookingguide.base.BaseFragmentWithBinding
import com.example.manygameinapplication.R
import com.example.manygameinapplication.adapter.FragmentAdapter
import com.example.manygameinapplication.databinding.FragmentMainBinding
import com.example.manygameinapplication.inapp.PurchaseInAppActivity
import com.example.manygameinapplication.local.Preference
import com.example.manygameinapplication.ui.custom.animation.ZoomOutPageTransformer
import com.example.manygameinapplication.ui.fragment.arcade.FragmentArcadeGame
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator

@Suppress("DEPRECATION")
class FragmentMain : BaseFragmentWithBinding<FragmentMainBinding>(
    FragmentMainBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentMain()
    }

    private val viewModel: MainViewModel by viewModels()
    private var adapter: FragmentAdapter? = null
    var isCheck: Boolean = false
    private var preference: Preference? = null

    override fun initAction() {
        preference = Preference.buildInstance(requireActivity())
        adapter = FragmentAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.ViewPagerLayout.adapter = adapter
        binding.ViewPagerLayout.setPageTransformer(ZoomOutPageTransformer())
        setupTabLayout(isCheck = false).attach()
        onTabSelected(binding.TableTitle, binding.ViewPagerLayout)
        binding.coin.text = preference?.getValueCoin().toString()
        binding.ClStore.setOnClickListener {
            startActivity(Intent(requireActivity(), PurchaseInAppActivity::class.java))
            requireActivity().finish()
        }

    }

    override fun initData() {
        viewModel.listFragment.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
            binding.ViewPagerLayout.offscreenPageLimit = it.size
        }
    }

    private fun setupTabLayout(isCheck: Boolean): TabLayoutMediator {
        return TabLayoutMediator(binding.TableTitle, binding.ViewPagerLayout) { tab, pos ->
            tab.setCustomView(R.layout.custom_title_tablayout)
            setIconTitleTab(tab, setIcon(pos), setText(pos), isCheck)
        }
    }

    private fun onTabSelected(tabLayout: TabLayout, viewPager: ViewPager2) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    isCheck = false
                    FragmentArcadeGame.hideGoneLayout(this@FragmentMain.isCheck)
                    configDialogNoti()
                } else {
                    isCheck = false
                    FragmentArcadeGame.hideGoneLayout(this@FragmentMain.isCheck)
                }
            }
        })

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: Tab?) {
                if (tab?.text == "Arcade") {
                    isCheck = false
                    configDialogNoti()
                } else {
                    isCheck = false
                }
            }

            override fun onTabUnselected(tab: Tab?) {
                if (tab?.position == 2) {
                    Handler().postDelayed({
                        isCheck = false
                        setupTabLayout(this@FragmentMain.isCheck).attach()
                    }, 100)
                }
            }

            override fun onTabReselected(tab: Tab?) {
            }

        })
    }

    private fun configDialogNoti(): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.custom_dialog_noti)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialog.findViewById<Button>(R.id.btnCancel)?.setOnClickListener {
            dialog.dismiss()
            isCheck = false
        }
        dialog.findViewById<Button>(R.id.btnOk)?.setOnClickListener {
            if (preference != null) {
                if ((preference?.getValueCoin() ?: 0) >= 2) {
                    dialog.dismiss()
                    isCheck = true
                    FragmentArcadeGame.hideGoneLayout(this.isCheck)
                    setupTabLayout(isCheck).attach()
                    preference?.setValueCoin(
                        preference?.getValueCoin()?.minus(2) ?: 0
                    )
                    binding.coin.text = preference?.getValueCoin().toString()
                } else {
                    dialog.dismiss()
                    val alertDialog = AlertDialog.Builder(requireActivity())
                    alertDialog.setTitle("You don't have enough gold ")
                        .setMessage("Open shop to buy more gold")
                        .setPositiveButton("Yes") { _, _ ->
                            configDialogNoti().dismiss()
                            val intent =
                                Intent(requireActivity(), PurchaseInAppActivity::class.java)
                            requireActivity().startActivity(intent)
                        }
                    alertDialog.setNegativeButton("Cancel") { _, _ ->
                        configDialogNoti().dismiss()
                    }
                    alertDialog.show()
                }
            }
        }
        if (!dialog.isShowing) {
            dialog.show()
        }
        return dialog
    }

    @SuppressLint("CutPasteId")
    private fun setIconTitleTab(tab: Tab, icon: Int, title: String, isCheck: Boolean) {
        if (title == "Arcade") {
            tab.customView?.findViewById<ImageView>(R.id.tab_icon)
                ?.setImageResource(R.drawable.locked)
            tab.customView?.findViewById<TextView>(R.id.tab_title)?.apply {
                visibility = View.GONE
                text = title
            }
            if (isCheck) {
                tab.customView?.findViewById<ImageView>(R.id.tab_icon)
                    ?.setImageResource(icon)
                tab.customView?.findViewById<TextView>(R.id.tab_title)?.visibility = View.VISIBLE
            }
        } else {
            tab.customView?.findViewById<ImageView>(R.id.tab_icon)?.setImageResource(icon)
            tab.customView?.findViewById<TextView>(R.id.tab_title)?.text = title
        }
    }

    private fun setText(pos: Int): String {
        return viewModel.arrTitleTab.value?.getOrNull(pos) ?: ""
    }

    private fun setIcon(pos: Int): Int {
        return viewModel.arrIconTab.value?.getOrNull(pos) ?: 0
    }
}