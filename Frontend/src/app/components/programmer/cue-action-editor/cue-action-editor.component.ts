import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-cue-action-editor',
  templateUrl: './cue-action-editor.component.html',
  styleUrls: ['./cue-action-editor.component.scss']
})
export class CueActionEditorComponent implements OnInit {

  @Input()
  public action: any;

  constructor() { }

  ngOnInit() {
  }

}
