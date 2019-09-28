import { Component, Input, Inject, OnInit } from '@angular/core';
import { IButtonPanel } from 'src/app/model/panels/IButtonPanel';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ICueList } from 'src/app/model/panels/ICueList';
import { CueListService } from 'src/app/services/CueListService';


@Component({
  templateUrl: './buttoneditdialog.component.html',
  styleUrls: ['./buttoneditdialog.component.scss']
})
export class ButtonEditDialogComponent implements OnInit {

  public cuelists: ICueList[];

  constructor(public dialogRef: MatDialogRef<ButtonEditDialogComponent>,
              public cueListService: CueListService,
              @Inject(MAT_DIALOG_DATA) public data: IButtonPanel) {
                console.log(data);
  }

  public async ngOnInit() {
    this.cuelists = await this.cueListService.getCueLists();
  }

  public getCues(cueList: ICueList) {
    if (!cueList) {
      return [];
    }
    return cueList.cues;
  }
}
