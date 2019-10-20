import { Component, OnInit, Input } from '@angular/core';
import { ICueList } from 'src/app/model/panels/ICueList';

@Component({
  selector: 'app-cue-list-editor',
  templateUrl: './cue-list-editor.component.html',
  styleUrls: ['./cue-list-editor.component.scss']
})
export class CueListEditorComponent implements OnInit {

  @Input()
  public cueList: ICueList;

  constructor() { }

  ngOnInit() {
  }

}
