import { IPanel } from './IPanel';


export interface IButtonPanel extends IPanel {
    styles: IButtonStyles;
    action: IButtonAction;
    description: string;
}

export interface IButtonStyles {
    backgroundActive: string;
    backgroundInactive: string;
    colorActive: string;
    colorInactive: string;
}

export interface IButtonAction {
    type: string;
    channel: number;
    value: number;
}
