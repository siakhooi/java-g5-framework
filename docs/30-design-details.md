# Some Design Details

## Type of Forms

- Blank Form - the mother form of every other form, extensible to new type of forms.
- Image Form
- Text Form
- Styled Text Form
- Menu Form
- System Tray Form
- Tab Form
- Helper/Selection Form
- Process Form
- Entry Form
  - List Form
  - Entry Form
  - Mode Entry Form
    - the form that manage 4 common mode/action in business applications
      - QUERY
      - VIEW
      - EDIT
      - DELETE action
- Report Execution Forms
  - Special type of Entry form that accept parameters of Reports and execute reports logic (written in JasperReports).

## Other

- Some of the class names use in this project might not follow what conventional Java class naming practices. This is because I was trying to mimik the way ERP systems naming the programs based on module and table names.
  - eg: `BSBJ0CMP` means
    - `BS` - application name
    - `B` - module group B
    - `J` - type of form - (for this case, process form type)
    - `0` - first form of the type (for this case, first process form)
    - `CMP` - the table name `BSCMP`.
  - This is inspired by the ERP system that I know of that time, such as Infimacs, ChESS, SAP.
