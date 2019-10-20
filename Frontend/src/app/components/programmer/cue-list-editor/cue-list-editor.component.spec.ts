import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CueListEditorComponent } from './cue-list-editor.component';

describe('CueListEditorComponent', () => {
  let component: CueListEditorComponent;
  let fixture: ComponentFixture<CueListEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CueListEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CueListEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
