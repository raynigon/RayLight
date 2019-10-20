import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CueActionEditorComponent } from './cue-action-editor.component';

describe('CueActionEditorComponent', () => {
  let component: CueActionEditorComponent;
  let fixture: ComponentFixture<CueActionEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CueActionEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CueActionEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
