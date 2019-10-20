import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-cue-editor',
  templateUrl: './cue-editor.component.html',
  styleUrls: ['./cue-editor.component.scss']
})
export class CueEditorComponent implements OnInit {

  @Input()
  public cue: any;

  constructor() { }

  ngOnInit() {
  }

}
