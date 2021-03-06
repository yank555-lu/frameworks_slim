/*
 * Copyright (C) 2016-2017 SlimRoms Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.slim.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.slim.settings.SlimActionShortcut;
import slim.action.ActionsArray;
import slim.action.ActionHelper;
import slim.utils.ImageHelper;

import com.slim.settings.R;

public class CreateSlimShortcut extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionsArray actionsArray = new ActionsArray(this, false, false);
        final String[] dialogValues = actionsArray.getValues();
        final String[] dialogEntries = actionsArray.getEntries();
        AlertDialog.Builder action = new AlertDialog.Builder(this);
        action.setTitle(R.string.shortcut_action_select_action)
        .setNegativeButton(android.R.string.cancel,
            new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                CreateSlimShortcut.this.finish();
            }
        })
        .setItems(dialogEntries,
            new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Intent shortcutIntent = new Intent(
                        CreateSlimShortcut.this, SlimActionShortcut.class);
                shortcutIntent.putExtra("value", dialogValues[item]);

                Drawable icon = ActionHelper.getActionIconImage(
                        CreateSlimShortcut.this, dialogValues[item], null);
                Intent intent = new Intent();
                intent.putExtra(Intent.EXTRA_SHORTCUT_ICON,
                        ImageHelper.drawableToShortcutIconBitmap(
                        getApplicationContext(), icon, 36 /* dp */));
                intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, dialogEntries[item]);
                intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
                setResult(RESULT_OK, intent);
                CreateSlimShortcut.this.finish();
            }
        });
        action.show();
    }
}
