import { Component, Input, Inject } from '@angular/core';
import { IButtonPanel } from 'src/app/model/panels/IButtonPanel';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';


@Component({
  templateUrl: './buttoneditdialog.component.html',
  styleUrls: ['./buttoneditdialog.component.scss']
})
export class ButtonEditDialogComponent {

  constructor(public dialogRef: MatDialogRef<ButtonEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: IButtonPanel) {
                console.log(data);
  }
}
