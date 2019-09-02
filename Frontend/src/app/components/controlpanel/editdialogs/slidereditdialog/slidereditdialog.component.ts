import { Component, Input, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ISliderPanel } from 'src/app/model/panels/ISliderPanel';


@Component({
  templateUrl: './slidereditdialog.component.html',
  styleUrls: ['./slidereditdialog.component.scss']
})
export class SliderEditDialogComponent {

  constructor(public dialogRef: MatDialogRef<SliderEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: ISliderPanel) {
                console.log(data);
  }
}
