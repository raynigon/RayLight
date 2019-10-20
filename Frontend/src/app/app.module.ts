import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AngularSplitModule } from 'angular-split';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/root/app.component';
import { DashboardComponent } from './components/programmer/dashboard/dashboard.component';
import { ControlPanelComponent } from './components/controlpanel/controlpanel/controlpanel.component';
import { ControlPanelService } from './services/ControlPanelService';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
  MatButtonModule,
  MatIconModule,
  MatMenuModule,
  MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatInputModule,
  MatSelectModule,
  MatToolbarModule,
  MatListModule
} from '@angular/material';
import { DMXService } from './services/DMXService';
import { ButtonPanelComponent } from './components/controlpanel/buttonpanel/buttonpanel.component';
import { SliderPanelComponent } from './components/controlpanel/sliderpanel/sliderpanel.component';
import { ButtonEditDialogComponent } from './components/controlpanel/editdialogs/buttoneditdialog/buttoneditdialog.component';
import { FormsModule } from '@angular/forms';
import { SliderEditDialogComponent } from './components/controlpanel/editdialogs/slidereditdialog/slidereditdialog.component';
import { CueListService } from './services/CueListService';
import { ShowService } from './services/ShowService';
import { ProgrammerContainerComponent } from './components/programmer/programmer-container/programmer-container.component';
import { ProgrammerViewComponent } from './components/programmer/programmer-view/programmer-view.component';
import { CueListEditorComponent } from './components/programmer/cue-list-editor/cue-list-editor.component';
import { CueEditorComponent } from './components/programmer/cue-editor/cue-editor.component';
import { CueActionEditorComponent } from './components/programmer/cue-action-editor/cue-action-editor.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ControlPanelComponent,
    ButtonPanelComponent,
    SliderPanelComponent,
    ButtonEditDialogComponent,
    SliderEditDialogComponent,
    ProgrammerContainerComponent,
    ProgrammerViewComponent,
    CueListEditorComponent,
    CueEditorComponent,
    CueActionEditorComponent,
  ],
  imports: [
    AngularSplitModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
    MatMenuModule,
    MatDialogModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatToolbarModule,
    MatListModule,
  ],
  entryComponents: [
    ButtonEditDialogComponent,
    SliderEditDialogComponent,
  ],
  providers: [ControlPanelService, DMXService, CueListService, ShowService],
  bootstrap: [AppComponent]
})
export class AppModule {}
