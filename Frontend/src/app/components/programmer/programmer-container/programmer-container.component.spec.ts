import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProgrammerContainerComponent } from './programmer-container.component';

describe('ProgrammerContainerComponent', () => {
  let component: ProgrammerContainerComponent;
  let fixture: ComponentFixture<ProgrammerContainerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProgrammerContainerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProgrammerContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
