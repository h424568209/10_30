import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Contact contact = new Contact();
        while(true){
            meue();
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select){
                case 1:
                    add(contact,scanner);
                    System.out.println("DeBug: "+ contact.toString());
                    break;
                case 2:
                    search(contact , scanner);
                    break;
                case 3:
                    remove(contact,scanner);
                    System.out.println("DeBug: "+ contact.toString());
                    break;
                case 4:
                    update(contact,scanner);
                    System.out.println("DeBug: "+ contact.toString());
                    break;
                    default:
                        System.out.println("Error");
            }
        }
    }
    private static void update(Contact contact, Scanner scanner) {
        System.out.println("请输入要更新的姓名:");
        String name = scanner.nextLine();
        System.out.println("请选择要更新的字段");
        System.out.println("1. Name");
        System.out.println("2. MobilePhoneNumber");
        System.out.println("3. OfficePhoneNumber");
        int select = scanner.nextInt();
        scanner.nextLine();
        switch (select){
            case 1 :{
                System.out.println("Input New Name :");
                String newname = scanner.nextLine();
                User user = contact.get(name);
                if(user == null){
                    System.out.println("Not Exist!");
                }else{
                    try {
                            contact.add(newname,user.mobilePhoneNumber,user.officePhoneNumber);
                            try {
                                contact.remove(name);
                            }catch (NameNotExistException e){
                            }
                    }catch (NameExistExceptioin e){
                        System.out.println("Name has Already exist!");
                    }
                }
                break;
            }
            case 2:{
                System.out.println("Input newMobilePhoneNumber:");
                String mobilePhonenumber = scanner.nextLine();
                User user = contact.get(name);
                if(user == null){
                    System.out.println("Name Not Exist");
                }else{
                    user.mobilePhoneNumber = mobilePhonenumber;
                    System.out.println("Succes Update");
                }
                break;
            }
            case 3:{
                System.out.println("Input newOfficeNumber:");
                String officePhoneNumber = scanner.nextLine();
                User user = contact.get(name);
                if(user == null){
                    System.out.println("Not Exist");
                }
                else{
                    user.officePhoneNumber = officePhoneNumber;
                    System.out.println("Success Update");
                }
                break;
            }
                default:
                    System.out.println("Error input indicitor");
        }
    }
    private static void remove(Contact contact, Scanner scanner) {
        System.out.println("请输入姓名:");
        String name = scanner.nextLine();
        try{
            contact.remove(name);
            System.out.println("Success Remove");
        }catch (NameNotExistException e){
            System.out.println("Name not Exist!");
        }
    }

    private static void search(Contact contact, Scanner scanner) {
        System.out.println("请输入姓名:");
        String name = scanner.nextLine();
        User  user = contact.get(name);
        if(user == null){
            System.out.println("Error Search!Not Exist");
        }else{
            System.out.println("PhoneNumber :" +user.mobilePhoneNumber);
            System.out.println("OfficeNumber :" + user.officePhoneNumber);
        }

    }

    private static void meue(){
        System.out.println("| 1. 添加");
        System.out.println("| 2. 查找");
        System.out.println("| 3. 删除");
        System.out.println("| 4. 更新");
        System.out.println("请选择正确的选项:");
    }
    private static void add(Contact contact, Scanner scanner) {
        System.out.println("请输入姓名:");
        String name = scanner.nextLine();
        System.out.println("请输入手机:");
        String mobilePhone = scanner.nextLine();
        System.out.println("请输入办公室电话:");
        String officePhone = scanner.nextLine();
        try{
            contact.add(name,mobilePhone,officePhone);
            System.out.println("Add success");
        }catch (NameExistExceptioin e){
            System.out.println("Exist! Default Adding");
        }finally {
            System.out.println("***********");
        }
    }
}
