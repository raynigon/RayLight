import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './components/programmer/dashboard/dashboard.component';
import { ControlPanelComponent } from './components/controlpanel/controlpanel/controlpanel.component';
import { ProgrammerContainerComponent } from './components/programmer/programmer-container/programmer-container.component';


const routes: Routes = [
  {
    path: '',
    component: ProgrammerContainerComponent
  },
  {
    path: 'controlpanel/:id',
    component: ControlPanelComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
