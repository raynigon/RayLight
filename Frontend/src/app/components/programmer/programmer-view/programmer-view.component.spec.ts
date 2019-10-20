import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgrammerViewComponent } from './programmer-view.component';

describe('ProgrammerViewComponent', () => {
  let component: ProgrammerViewComponent;
  let fixture: ComponentFixture<ProgrammerViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProgrammerViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgrammerViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
